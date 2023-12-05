const { createApp } = Vue;

createApp({
  data() {
    return {
      books:[],      
      categories:[],
    
      checked:[],
      topSelling:[],
      inputValue:"",
        // order: {
        //   address: "",
        //   books: [],
        // },
      cart:[],
      total:0,
    }
  },

  created(){
 
  

    axios.get("/api/books")
    .then(response => {
      console.log(response)

      //cart
      this.cart = JSON.parse(localStorage.getItem("cart")) || [];
      JSON.stringify(this.cart)


      for (book of this.cart){
        this.total += book.price * book.quantity

      }

     for (book of this.cart){
      let aux = response.data.find(itemCart => itemCart.id == book.id)
      aux.stock -= book.quantity


      
     }
      //endcart

      this.books = response.data;

      }
   )
    .catch(error => console.log(error))

  },

  methods:{
  

  checkFilter(books,categories){
    if(categories.length == 0){
      return books
    }

    return books.filter( book => categories.includes(book["categories"]))

  },

  mainFilter(){
    const inputFilter = this.searchFilter(this.books,this.inputValue)
    const  checkboxFilter =  this.checkFilter(inputFilter,this.checked)
    this.filter = checkboxFilter;
  },

  addToCartAndUpdateOrder(book) {
    this.addcart(book); // Primero, agrega el libro al carrito
    this.updateOrderBooks(); // Luego, actualiza order.books
},

// updateOrderBooks() {
//   this.order.books = this.cart.map(item => ({
//       id: item.id,
//       quantity: item.quantity,
//       price: item.price
//   }));

// },

  addCart(bookA){
   let index = this.cart.findIndex(book => book.id == bookA.id)

   if(index == -1){
    bookA.quantity = 1;
    this.cart.push(bookA)

   }  else {
    this.cart[index].quantity += 1
   }

   this.total += bookA.price
    
  bookA.stock -= 1;
  localStorage.setItem("cart",JSON.stringify(this.cart))
  },

  deletefromCart(bookD){
    console.log(bookD.stock)
     let index = this.cart.findIndex(book => book.id == bookD.id)
     //elemina 1 elemente desde index
     this.cart.splice(index, 1)
  
     localStorage.setItem("cart", JSON.stringify(this.cart))
     bookD.stock += bookD.quantity

     console.log(bookD.stock)

     this.total -= bookD.quantity * bookD.price

  },

  lessCart(bookL){
    let index = this.cart.findIndex(book => book.id == bookL.id)

    this.cart[index].quantity -= 1

    this.total -= bookL.price 

    if( this.cart[index].quantity <= 0){
      this.cart.splice(index, 1)
    }

    bookL.stock += 1;
    localStorage.setItem("cart",JSON.stringify(this.cart))
  }



  }



}).mount('#app')