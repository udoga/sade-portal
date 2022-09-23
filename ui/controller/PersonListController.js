class PersonListController extends ListController {
  people = [];

  constructor(api, notifier) {
    super(api, "people", notifier);
  }
}
