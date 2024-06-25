class RoleClient extends RestClient {
    constructor() {
        super("/roles/");
    }

    listByUserId = function (userId) {
        return this.getJson(this.get(this.url + "?userId=" + userId, { headers: this.getHeaders() }));
    }
}
