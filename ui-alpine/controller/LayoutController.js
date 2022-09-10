class LayoutController {
  constructor() {
    this.navigate('home');
  }

  navigate = (viewName) => {
    axios.get('/view/' + viewName + '.html').then((response) => {
      this.$refs.main.innerHTML = response.data;
    });
  }
}
