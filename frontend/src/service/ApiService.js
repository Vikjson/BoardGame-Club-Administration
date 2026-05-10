class ApiService {
    constructor() {
        this.apiUrl = 'http://localhost:32772/boardgame-club-administration/home'
    }

    async getData(endpoint) {

        const response = await fetch(this.apiUrl + endpoint);

        if (!response.ok) {
            throw new Error(`Problem fetching: ${response.status}`);
        }

        return await response.json();
    }

    async postData(endpoint, entity) {
        const resp = await fetch(this.apiUrl + endpoint, {
            method: "POST",
            body: JSON.stringify(entity),
        })
    }
}

export default ApiService;