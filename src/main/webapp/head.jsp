<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/fontawesome.min.css"
      integrity="sha512-UuQ/zJlbMVAw/UU8vVBhnI4op+/tFOpQZVT+FormmIEhRSCnJWyHiBbEVgM4Uztsht41f3FzVWgLuwzUqOObKw=="
      crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />

<%--<link rel="stylesheet" href="dist/styles.css">--%>
<script src="https://cdn.tailwindcss.com"></script>
<script>
    tailwind.config = {
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
                aspectRatio:{
                    'banner': '[9/16]',
                }
            },
        },
    }
</script>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/img/figuras/popcorn.ico" type="image/x-icon">


