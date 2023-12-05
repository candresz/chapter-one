let { createApp } = Vue;

createApp({
  data() {
    return {
      topFiction: [],
      topKids: [],
      topAutobiography: []
    };
  },

  created() {
    this.getData();
    alert("hola")
  },

  methods: {
    getData(){
      axios.get('/api/books')
      .then( ({data}) => {
        this.topFiction = data.filter(book => book.categories == 'Fiction').sort((a, b) => b.totalSales - a.totalSales).slice(0, 5)
        this.topKids = data.filter(book => book.categories == 'Kids').sort((a, b) => b.totalSales - a.totalSales).slice(0, 3)
        this.topAutobiography = data.filter(book => book.categories == 'Autobiography').sort((a, b) => b.totalSales - a.totalSales).slice(0, 3)
      })
      .catch(err => console.log(err))
    },

  },
  
  computed:{

  }

}).mount("#app");