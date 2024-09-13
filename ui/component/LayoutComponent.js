class LayoutComponent {
    sessionApi = null;
    isWide = true;
    hasNavigation = true;
    isShifted = true;
    hasOverlay = false;
    selectedTitle = "";
    username = "";
    employee = null;

    constructor(sessionApi) {
        this.sessionApi = sessionApi;
        this.handleResize();
    }

    load = function(userApi, employeeApi) {
        var userId = localStorage.getItem("userId");
        userApi.read(userId).then(json => { if (json.data) this.username = json.data.username });
        employeeApi.list("", {"userId": userId}).then(json => { if (json.data) this.employee = json.data[0] });
    }

    logout = function () {
        this.sessionApi.logout();
        window.dispatchEvent(new CustomEvent("logout-event"));
    }

    handleResize = function () {
        if (this.isWide !== (window.innerWidth > 768)) this.toggleWide();
    }

    handleHashChange = function () {
        if (!this.isWide) this.setNavigation(false);
    }

    toggleWide = function () {
        this.isWide = !this.isWide;
        this.setNavigation(this.isWide);
    }

    toggleNavigation = function () {
        this.setNavigation(!this.hasNavigation);
    }

    setNavigation = function (hasNavigation) {
        this.hasNavigation = hasNavigation;
        this.isShifted = this.isWide && this.hasNavigation;
        this.hasOverlay = !this.isWide && this.hasNavigation;
    }

    select = function (title) {
        this.selectedTitle = title;
    }

    getClass = function (title) {
        return (this.selectedTitle === title)? "selected" : "";
    }

    getEmployeeQuery = function () {
        return (this.employee && this.employee.id)? ("?employeeId=" + this.employee.id) : "";
    }
}
