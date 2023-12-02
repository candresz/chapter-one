const { createApp } = Vue;

  createApp({
    data() {
      return {
        book: [],
        id: "",
      };
    },
    created() {
      const parameters = location.search;
      const parametersKeyValue = new URLSearchParams(parameters);
      this.id = parametersKeyValue.get("id");

      axios.get(`/api/books/${this.id}`) 
        .then(({ data }) => {
          this.book = data; 
          console.log(this.book);
        })
        .catch((error) => {
          console.error(error);
        });
    },
  }).mount("#app");