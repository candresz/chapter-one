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
        })
        .catch(error => {
          console.log(error);
        });
      }
    }
  });
  