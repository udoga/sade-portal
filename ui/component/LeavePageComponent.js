class LeavePageComponent extends PageComponent {
  leave = this.record;

  constructor(router, client) {
    super(router, client, "leave");
  }
}
