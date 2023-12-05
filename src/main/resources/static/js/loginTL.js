const { createApp } = Vue;
createApp({
  data() {
    return {
        form:"register",

        emailL:"",
        passwordL:"",

        name:"",
        lastName:"",
        email:"",
        password:""

    }
  },

  created(){

    // alert("hola")


  },

  methods:{
  

  best3(){
    const array = Array.from(this.books)
    array.sort((a,b) => b.totalSales - a.totalSales)

    console.log(array)
    const best = [array[0],array[1], array[2]]
    console.log(best)
    return best
  },


  logIn(){
    console.log(this.emailL)
    console.log(this.passwordL)
  },

  register(){
   console.log(this.name)
   console.log(this.lastName)
   console.log(this.email)
   console.log(this.password)

    const data = `firstName=${this.name}&lastName=${this.lastName}&email=${this.email}&password=${this.password}`
   axios.post("/api/clients" , data)
   .then(response => {
   
  

      axios.post("/api/login", `email=${this.email}&password=${this.password}`)
      .then(data =>{
        console.log("sign in!")
        
        Swal.fire({
          icon: "success",
          title: "Your work has been saved",
          showConfirmButton: false,
          timer: 1300 
        });

        setTimeout(() => {
          
          location.href ="./user.html"

        }, 1400);


      
    }).catch(error =>  
     console.log(error))


    })//end of login


    .catch(error => 
      Swal.fire({
      icon: "error",
      title: "Oops...",
      text: error.response.data,
      // footer: '<a href="#">Why do I have this issue?</a>'
    }))
  
  
  },


  login(){
    axios.post("/api/login", `email=${this.emailL}&password=${this.passwordL}`)
      .then(data =>{
        console.log("sign in!")
        
        Swal.fire({
          icon: "success",
          title: "Login Successfully",
          showConfirmButton: false,
          timer: 1300 
        });

        setTimeout(() => {
          
          location.href ="./user.html"

        }, 1400);


      
    }).catch(error => Swal.fire({
      icon: "error",
      title: "Oops...",
      text: error.data,
    }))


    
  },

  showform(form){
    this.form = form

  }








  }



}).mount('#app')