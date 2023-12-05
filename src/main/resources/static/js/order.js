new Vue({
    el: '#app',
    data: {
      client: {}
    },
    created: function() {
      this.fetchClientData();
    },
    methods: {
      fetchClientData: function() {
        axios.get('/api/clients/current')
        .then(response => {
          this.client = response.data;
          console.log(response.data)
        })
        .catch(error => {
          console.log(error);
        });
      },
      formatDate: function(dateTime) {
        // Asumiendo que dateTime viene en formato 'YYYY-MM-DDTHH:mm:ss'
        const options = { year: 'numeric', month: 'long', day: 'numeric' };
        return new Date(dateTime).toLocaleDateString('en-US', options);
      },
      processTime: function(dateTime) {
        // Extrae la hora de la fecha en formato 'HH:mm:ss'
        return new Date(dateTime).toLocaleTimeString('en-US', { hour12: true });
      }
    }
  });
  