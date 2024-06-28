class AssignmentApi extends RestApi {
    constructor() {
        super("/assignments/");
    }

    listWithRoleByUserId = function (userId) {
      return this.getJson(this.get(this.url + "with-role?userId=" + userId, { headers: this.getHeaders() }));
    }
}
