class PersonPageController extends PageController {
  person = {};

  constructor(router, api) {
    super(router, api, "person");
  }

  getRecord = function() {
    return this.person;
  }
}
