class ApiService {
    constructor() {
        this.apiUrl = 'http://localhost:32772/boardgame-club-administration/home'
    }

    async request(endpoint, options = {}) {
        const response = await fetch(this.apiUrl + endpoint, options)

        if (!response.ok) {
            throw new Error(`Request failed: ${response.status}`)
        }

        if (response.status === 204) {
            return null
        }

        const text = await response.text()

        if (!text) {
            return null
        }

        return JSON.parse(text)
    }

    getData(endpoint) {
        return this.request(endpoint)
    }

    postData(endpoint, data) {
        return this.request(endpoint, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data)
        })
    }

    putData(endpoint) {
        return this.request(endpoint, {
            method: 'PUT'
        })
    }

    deleteData(endpoint) {
        return this.request(endpoint, {
            method: 'DELETE'
        })
    }
}

export default ApiService