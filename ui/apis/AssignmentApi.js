class AssignmentApi extends RestApi {
    constructor() {
        super("/assignments/");
    }

    listWithRoleByUserId = function (userId) {
        return this.list("with-role?userId=" + userId);
    }
}
