class PersonApi extends RestApi {
  constructor(httpApi) {
    super(httpApi, "/api/people/");
  }
}
