const { createApp } = Vue;

createApp({
    data() {
        return {
            stripe: null,
            cardDetails: {
                number: '',
                exp_month: '',
                exp_year: '',
                cvc: ''
            },
            order: {
                address: '',
                books: [
                    { id: '', quantity: '', price: '' }
                ]
            }
        };
    },
    mounted() {
        this.stripe = Stripe('pk_test_51OHSzYA6XL9pQO8XITflE99c19vZBO2OJwG8ThwqVJu6MmKOepzZkvzrRAUprcLyR6Fc29FrBlB9L3tkEizUBTeB00RbYtNFem');
        const elements = this.stripe.elements();
    
        this.cardNumber = elements.create('cardNumber');
        this.cardNumber.mount('#card-number');
    
        this.cardExpiry = elements.create('cardExpiry');
        this.cardExpiry.mount('#card-exp');
    
        this.cardCvc = elements.create('cardCvc');
        this.cardCvc.mount('#card-cvc');
    },    
    methods: {
        addBook() {
            this.order.books.push({ id: '', quantity: '', price: '' });
        },
        async submitPayment() {
            const { token, error } = await this.stripe.createToken(this.cardNumber);
        
            if (error) {
                console.error(error);
            } else {
                axios.post('/api/orderDetail', {
                    address: this.order.address,
                    books: this.order.books,
                    stripeToken: token.id
                })
                .then(response => {
                    console.log(response.data);
                })
                .catch(error => {
                    console.error(error);
                });
            }
        }
    }
}).mount('#app');
