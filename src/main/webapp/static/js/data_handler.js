export let dataHandler = {

    _api_get: function (url, callback) {
        fetch(url, {
            method: 'GET',
            credentials: 'same-origin'
        })
            .then(response => response.json())  // parse the response as JSON
    .then(json_response => callback(json_response)); // Call the `callback` with the returned object
    },

    _api_post: function (url, data, callback) {


        fetch(url, {
            method: 'POST',
            credentials: 'same-origin',
            headers: {
                'Content-Type': 'application/json',
                // 'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: JSON.stringify(data)
        })
    .then(callback);  // Call the `callback` with the returned object
    },

    addToCart : function (id, callback) {

        let data = {'product_id': id};
        this._api_post(`/api/cart`, data, callback);
    }
};
