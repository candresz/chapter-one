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
    };
  },

  created(){
    // Coloca tu código JavaScript normal aquí
    const btnSignIn = document.getElementById("sign-in"),
          btnSignUp = document.getElementById("sign-up"),
          formRegister = document.querySelector(".register"),
          formLogin = document.querySelector(".login");

    btnSignIn.addEventListener("click", e => {
        formRegister.classList.add("hide"); 
        formLogin.classList.remove("hide");
    });

    btnSignUp.addEventListener("click", e => {
        formLogin.classList.add("hide"); 
        formRegister.classList.remove("hide");
    });

    // Nota: Manejar eventos de formularios directamente en Vue es más adecuado
  },

  methods:{
    // Tus métodos Vue.js van aquí
    logIn() {
      console.log(this.emailL);
      console.log(this.passwordL);
      // Aquí puedes añadir tu lógica de inicio de sesión
    },

    register() {
      console.log(this.name);
      console.log(this.lastName);
      console.log(this.email);
      console.log(this.password);
      // Aquí puedes añadir tu lógica de registro
    }


   

  }
}).mount('#app')