const { createApp } = Vue;
createApp({
  data() {
    return {
      books: [],
      categories: [],
      filter: [],
      checked: [],
      topSelling: [],
      inputValue: "",
      cart: [],
      total: 0,

      stripe: null,
      cardDetails: {
        number: "",
        exp_month: "",
        exp_year: "",
        cvc: "",
      },
      order: {
        address: "",
        books: [{ id: "", quantity: "", price: "" }],
      },
    };
  },
  created(){
    axios.get("/api/books")
    .then(response => {
      console.log(response)

      //cart

   
      this.cart = JSON.parse(localStorage.getItem("cart")) || [];
      this.updateOrderBooks();

      JSON.stringify(this.cart)

      for (book of this.cart){
        this.total += book.price

      }


     for (book of this.cart){
      let aux = response.data.find(itemCart => itemCart.id == book.id)
      aux.stock -= book.quantity

      
     }

      //endcart

      this.books = response.data;

      this.filter = this.books
      console.log(this.filter)

      let todasCat = this.books.map(book => book.categories)

      this.categories = this.OnlyElement(todasCat)
      console.log(this.categories)


      this.topSelling = this.best3()

      }



    )
    .catch(error => console.log(error))





  },
  mounted() {
    this.stripe = Stripe(
      "pk_test_51OHSzYA6XL9pQO8XITflE99c19vZBO2OJwG8ThwqVJu6MmKOepzZkvzrRAUprcLyR6Fc29FrBlB9L3tkEizUBTeB00RbYtNFem"
    );
    const elements = this.stripe.elements();

    this.cardNumber = elements.create("cardNumber");
    this.cardNumber.mount("#card-number");

    this.cardExpiry = elements.create("cardExpiry");
    this.cardExpiry.mount("#card-exp");

    this.cardCvc = elements.create("cardCvc");
    this.cardCvc.mount("#card-cvc");
  },
  methods: {
    addToCartAndUpdateOrder(book) {
        this.addcart(book); // Primero, agrega el libro al carrito
        this.updateOrderBooks(); // Luego, actualiza order.books
    },
    updateOrderBooks() {
      this.order.books = this.cart.map(item => ({
          id: item.id,
          quantity: item.quantity,
          price: item.price
      }));
    },
    
    addBook() {
      this.order.books.push({ id: "", quantity: "", price: "" });
    },
    async submitPayment() {
      const { token, error } = await this.stripe.createToken(this.cardNumber);

      if (error) {
        console.error(error);
      } else {
        axios
          .post("/api/orderDetail", {
            address: this.order.address,
            books: this.order.books,
            stripeToken: token.id,
          })
          .then((response) => {
            console.log(response.data);
          })
          .catch((error) => {
            console.error(error);
          });
      }
    },
    
    OnlyElement(lista){
        return Array.from(new Set (lista))
    },
  
    searchFilter(books,inputSearchvalue){
      return books.filter( book => book.name.toLowerCase().includes(inputSearchvalue.toLowerCase()))
    
  },
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
  
  
    best3(){
      const array = Array.from(this.books)
      array.sort((a,b) => b.totalSales - a.totalSales)
  
      console.log(array)
      const best = [array[0],array[1], array[2]]
      console.log(best)
      return best
    },
  
    addcart(bookA){
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
  
    deletefromcart(bookD){
      console.log(bookD.stock)
       let index = this.cart.findIndex(book => book.id == bookD.id)
       //elemina 1 elemente desde index
       this.cart.splice(index, 1)
    
       localStorage.setItem("cart", JSON.stringify(this.cart))
       bookD.stock += bookD.quantity
  
       console.log(bookD.stock)
  
       this.total -= bookD.quantity * bookD.price
  
    }
  },
}).mount("#app");
