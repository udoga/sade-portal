class PersonListController extends ListController {
  people = [];

  constructor(client, notifier) {
    super(client, "people", notifier);
  }
}
