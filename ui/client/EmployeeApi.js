class EmployeeApi extends RestApi {
    constructor() {
        super("/employees/");
    }

    listByUserId = function (userId) {
        return this.getJson(this.get(this.url + "?userId=" + userId, { headers: this.getHeaders() }));
    }
}
