class LayoutComponent {
    sessionApi = null;
    isWide = true;
    hasNavigation = true;
    isShifted = true;
    hasOverlay = false;

    constructor(sessionApi) {
        this.sessionApi = sessionApi;
        this.handleResize();
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
}
