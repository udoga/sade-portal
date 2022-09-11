class LayoutController {
  isWide = true;
  hasNavigation = true;
  isShifted = true;
  hasOverlay = false;

  constructor() {
    this.onResize();
    this.onHashChange();
  }

  onResize = function() {
    if (this.isWide !== (window.innerWidth > 768)) this.toggleWide();
  }

  onHashChange = function() {
    if (!location.hash) history.pushState(null, null, "#/home");
    else if (location.hash === "#/home") this.loadView("home");
    else if (location.hash === "#/users") this.loadView("user-list");
    else this.loadView("not-found");
  }

  toggleWide = function() {
    this.isWide = !this.isWide;
    this.setNavigation(this.isWide);
  }

  toggleNavigation = function() {
    this.setNavigation(!this.hasNavigation);
  }

  setNavigation = function(hasNavigation) {
    this.hasNavigation = hasNavigation;
    this.isShifted = this.isWide && this.hasNavigation;
    this.hasOverlay = !this.isWide && this.hasNavigation;
  }

  loadView = function(viewName) {
    axios.get('/view/' + (viewName) + '.html').then((response) => { this.$refs.main.innerHTML = response.data; });
    if (!this.isWide) this.setNavigation(false);
  }
}
