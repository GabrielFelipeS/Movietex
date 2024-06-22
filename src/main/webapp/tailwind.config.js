/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./*.{html,js}"],
  theme: {
    screens:{
      'sm': '640px',
      'md': '1000px',
      'lg': '1200px',
      'xl': '1480px',
    },
    extend: {
      colors: {
        'primary': '#CF2028',
        'secondary': '#FFB71B',
        'danger': '#e3342f',
      },
    },
  },
  plugins: [],
}