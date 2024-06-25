class Notifier {
    notifications = [];

    notify = function (notification) {
        this.notifications.push(notification);
        this.emit();
    }

    notifyLater = function (notification) {
        setTimeout(() => this.notify(notification), 100);
    }

    clear = function () {
        this.notifications = [];
        this.emit();
    }

    emit = function () {
        window.dispatchEvent(new CustomEvent("notifications-event", { detail: this.notifications.slice() }));
    }
}
