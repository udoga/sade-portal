class PersonPageController extends PageController {
  person = {};

  constructor(router, client) {
    super(router, client, "person");
  }

  getRecord = function() {
    return this.person;
  }
}
