/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        // 11st Timedeal Colors - Primary Sale Colors
        'sale-red': '#ff0038',
        'sale-red-alt': '#f62e3d',
        'sale-red-bright': '#ff334c',

        // Secondary Colors
        'primary-blue': '#0b83e6',
        'navy-blue': '#3a4370',
        'success-green': '#249356',
        'warning-orange': '#ff8100',
        'premium-purple': '#6423ff',

        // Neutral Colors
        'text-primary': '#111111',
        'text-secondary': '#333333',
        'text-tertiary': '#666666',
        'text-meta': '#999999',
        'border-default': '#dadada',
        'border-light': '#f0f0f0',
        'bg-gray': '#f5f5f5',

        // Badge Colors
        'badge-red': '#ff0038',
        'badge-blue': '#0b83e6',
        'badge-navy': '#3a4370',
        'badge-green': '#249356',
        'badge-orange': '#ff8100',
        'badge-purple': '#6423ff',
        'badge-gray': '#999999',
      },
      fontFamily: {
        sans: ['Noto Sans KR', 'Helvetica Neue', 'Arial', 'sans-serif'],
        number: ['Tahoma', 'Arial', 'sans-serif'],
      },
      fontSize: {
        'product-name': '16px',
        'sale-percent': '25px',
        'sale-price': '18px',
        'original-price': '12px',
        'badge-text': '12px',
        'timer': '18px',
      },
      spacing: {
        'card-padding': '16px',
      },
      borderRadius: {
        'card': '4px',
      },
    },
  },
  plugins: [],
}
