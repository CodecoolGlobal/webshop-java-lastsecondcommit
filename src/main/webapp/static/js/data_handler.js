export let dataHandler = {
    _data: {}, // it contains the boards and their cards and statuses. It is not called from outside.
    _api_get: function (url, callback) {
        // it is not called from outside
        // loads data from API, parses it and calls the callback with it

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
