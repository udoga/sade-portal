class LeavePageComponent extends PageComponent {
  leave = {};

  constructor(router, client) {
    super(router, client, "leave");
  }

  getRecord = function() {
    return this.leave;
  }
}
