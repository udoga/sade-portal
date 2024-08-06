class RestApi extends HttpClient {
    url = null;

    constructor(url) {
        super();
        this.url = "/api" + url;
    }

    list = function (appendedUrl = "", params = {}) {
        if (this.hasData(params)) appendedUrl += "?";
        return this.getJson(this.get(this.url + appendedUrl + new URLSearchParams(params),
                { headers: this.getHeaders() }));
    }

    create = function (data) {
        return this.getJson(this.post(this.url, { headers: this.getHeaders(), body: JSON.stringify(data) }));
    }

    read = function (id, appendedUrl = "") {
        return this.getJson(this.get(this.url + appendedUrl + id, { headers: this.getHeaders() }));
    }

    update = function (id, data) {
        return this.getJson(this.put(this.url + id, { headers: this.getHeaders(), body: JSON.stringify(data) }));
    }

    delete = function (id) {
        return this.getJson(this.delete_(this.url + id, { headers: this.getHeaders() }));
    }

    listDetailed = function (params = {}) {
        return this.list("detailed", params);
    }

    readDetailed = function (id) {
        return this.read(id, "detailed/");
    }

    getJson = function (request) {
        return request.then(response => response.json()).then(json => this.checkSession(json));
    }

    getHeaders = function () {
        return { "Content-Type": "application/json", "Authorization": this.getToken("Bearer ") };
    }

    getToken = function (prefix) {
        var token = localStorage.getItem("token");
        return token ? (prefix + token) : "";
    }

    checkSession = function (json) {
        var e = json.error;
        if (e && e.type === "SessionError" && e.message !== "Invalid username or password.")
            window.dispatchEvent(new CustomEvent("logout-event"));
        return json;
    }

    hasData = function (obj) {
        return Object.keys(obj).length !== 0;
    }
}
