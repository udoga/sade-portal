class PersonFormController extends FormController {
  person = {};

  constructor(router, api, notifier) {
    super(router, api, "person", notifier);
  }

  getRecord = function() {
    return this.person;
  }
}
