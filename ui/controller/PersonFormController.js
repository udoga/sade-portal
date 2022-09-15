class PersonFormController extends FormController {
  person = {};

  constructor(router, api) {
    super(router, api, "person");
  }

  getRecord = function() {
    return this.person;
  }
}
