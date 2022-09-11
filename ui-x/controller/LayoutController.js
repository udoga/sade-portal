class LayoutController {
  httpApi = null;
  isWide = true;
  hasNavigation = true;
  isShifted = true;
  hasOverlay = false;

  constructor(httpApi) {
    this.httpApi = httpApi;
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
    this.httpApi.get(viewPath).then(response => response.text()).then(html => { element.innerHTML = html; });
    if (!this.isWide) this.setNavigation(false);
  }
}
