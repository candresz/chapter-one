let { createApp } = Vue;

createApp({
  data() {
    return {
      best: [],
      top4Kids:[],
      fiction:[]
      
    };
  },

  created() {
    this.getData();

  },

  methods: {
    getData(){
      axios.get('/api/books')
      .then( ({data}) => {
        this.best = data.sort((a, b) => b.totalSales - a.totalSales).slice(0, 8)
       console.log(this.best)

       this.top4Kids = data.filter(book => book.categories == 'Kids').sort((a, b) => b.totalSales - a.totalSales).slice(0, 8)
       console.log(this.best)

       this.fiction = data.filter(book => book.categories == 'Fiction').sort((a, b) => b.totalSales - a.totalSales).slice(0, 8)
       console.log(this.fiction)
      })
      .catch(err => console.log(err))
    },

  },
  
  computed:{

  }

}).mount("#app");