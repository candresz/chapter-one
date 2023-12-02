const { createApp } = Vue;
createApp({
  data() {
    return {
        name:"",
        author:"",
        categories:"",
        price:"",
        stock:"",
        imageUrl:"",
        description:"",

    };
  },

  created(){
  
   

    
  },

  methods:{
    // Tus métodos Vue.js van aquí
    
    newBook(){
        const bookInfo = `name=${this.name}&author=${this.author}&categories=${this.categories}&price=${this.price}&stock=${this.stock}&imageUrl=${this.imageUrl}&description=${this.description}`
        axios.post("/api/books", bookInfo)
        .then(data =>{

            Swal.fire({
                position: "top-end",
                icon: "success",
                title: "the book has been saved",
                showConfirmButton: false,
                timer: 1300 
              });
      
              setTimeout(() => {
                
                location.href ="./store.html"
      
              }, 1400);

        })

        .catch(error => {
          console.log(error)
          
          Swal.fire({
          icon: "error",
          title: "Oops...",
          text: error.response.data,

        })
      }
        
        )
    
    }
   

  }
}).mount('#app')