class LayoutController {
  isWide = true;
  hasNavigation = true;
  isShifted = true;
  hasOverlay = false;

  constructor(router) {
    this.onResize();
  }

  onResize = function() {
    if (this.isWide !== (window.innerWidth > 768)) this.toggleWide();
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

  loadView = function(element, viewPath) {
    axios.get(viewPath).then(response => { element.innerHTML = response.data; });
    if (!this.isWide) this.setNavigation(false);
  }
}
