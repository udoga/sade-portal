class PersonFormController extends FormController {
  person = {};

  constructor(router, client, notifier) {
    super(router, client, "person", notifier);
  }

  getRecord = function() {
    return this.person;
  }
}
