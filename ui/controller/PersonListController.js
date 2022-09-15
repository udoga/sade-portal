class PersonListController extends ListController {
  people = [];

  constructor(api) {
    super(api, "people");
  }
}