class Notifier {
  notifications = [];

  notify = function(notification) {
    this.notifications.push(notification);
    this.emit();
  }

  clear = function() {
    this.notifications = [];
    this.emit();
  }

  emit = function() {
    window.dispatchEvent(new CustomEvent("notifications-event", { detail: this.notifications.slice() }));
  }
}
