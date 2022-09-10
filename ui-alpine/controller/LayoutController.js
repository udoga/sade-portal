class LayoutController {
  isWide = true;
  hasNavigation = true;
  isShifted = true;
  hasOverlay = false;

  constructor() {
    this.navigate('home');
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

  navigate = function(viewName) {
    axios.get('/view/' + viewName + '.html').then((response) => { this.$refs.main.innerHTML = response.data; });
    if (!this.isWide) this.setNavigation(false);
  }
}
