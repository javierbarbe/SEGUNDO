const CAR_PRODUCTS = "carProductsId";

document.addEventListener("DOMContentLoaded", () => {
  loadProducts();
  loadProductCart();
});

function getProductsDb() {
  const url = "../dbProductos.json";
  // hay que retornar el fetch  para que se almacene luego en la constante products de loadProducts()
  return fetch(url)
    .then((response) => {
      return response.json();
    })
    .then((result) => {
      return result;
    })
    .catch((err) => {
      console.log(err);
    });
}

// loadProducts tiene que ser asincrona ya que getProductsDb devuelve una promesa
async function loadProducts() {
  const products = await getProductsDb();
  let html = "";
  products.forEach((product) => {
    html += `
        <div class="col-3 product-container>
            <div class="card product">
                <img
                    src=${product.image}
                    class="card-img-top"
                    alt=${product.name}
                ></img>
                <div class="card-body">
                    <h5 class="card-title">${product.name}</h5>
                    <p class="card-text">${product.extraInfo}</p>
                    <p class="card-text"> ${product.price} €/Unidad </p>
                    <button type="button" class="btn btn-primary btn-cart" onclick="addProductCart(${product.id})">Añadir al carrito</button>
                </div>
            </div>
        
        </div>
      
      `;
  });
  document.getElementsByClassName("products")[0].innerHTML = html;
}

function openCloseCart() {
  const containerCartProducts = document.getElementsByClassName(
    "cart-products"
  )[0];
  containerCartProducts.classList.forEach((item) => {
    if (item === "hidden") {
      containerCartProducts.classList.remove("hidden");
      containerCartProducts.classList.add("active");
    }
    if (item === "active") {
      containerCartProducts.classList.remove("active");
      containerCartProducts.classList.add("hidden");
    }
  });
  //console.log(containerCartProducts);
}

function addProductCart(productId) {
  let arrayProductId = [];
  // creamos el localStorage
  let localStorageItems = localStorage.getItem(CAR_PRODUCTS);
  
  if (localStorageItems === null) {
    arrayProductId.push(productId);
    localStorage.setItem(CAR_PRODUCTS, arrayProductId);
  } else {
    arrayProductId.push(productId);
    localStorageItems = localStorage.getItem(CAR_PRODUCTS);
    let productsId = localStorageItems;

    productsId.toString().replace(/,,/,",");
    
    const tamanio= productsId.length;
    const ultimo= productsId[productsId.length-1];
 
    if (productsId.length > 0   ) {
      console.log(productsId);
      console.log(productsId[productsId.length-1]+ " es el valor del ultimo elemento de la lista");
      if(productsId.toString().replace(/,,/,",")){
        console.log("cambiado dos comas por una");
      }
      if(productsId[productsId.length-1]!=","){
        //console.log(productsId[productsId.length-1]+ " es el valor del ultimo elemento de la lista");
      productsId += "," + productId;
      }else{
        productsId += productId;
      }
    } else {
      productsId = productId;
    }
    localStorage.setItem(CAR_PRODUCTS, productsId);
  }
  loadProductCart();
}

async function loadProductCart() {
  let html = "";
  const products = await getProductsDb();
  //convertimos el resultado del localStorage en un array
  const localStorageItems = localStorage.getItem(CAR_PRODUCTS);
 
  if (localStorageItems == null ) {
    console.log("el carro esta vacio");
    html = `<div class="cart-product empty">
           <p> El carrito está vacío...   </p>     
    </div>
    `;
  } else {
    const idProductsSplit = localStorageItems.split(",");
    //  console.log(idProductsSplit);
    // eliminamos los ids REpetidos
    const idProductsCart = Array.from(new Set(idProductsSplit));

    idProductsCart.forEach((id) => {
      products.forEach((product) => {
        if (id == product.id) {
          const quantity = countDuplicatesId(id, idProductsSplit);
          const totalPrice = product.price * quantity;
          html += `
            <div class="cart-product">
                <img src= ${product.image}> 
                <div class="cart-product-info">
                    <span class="quantity">${quantity}</span>
                    <p>${product.name}</p>
                    <p>${totalPrice.toFixed(2)} €</p>
                    <p class="change-quantity">
                        <button onclick="removeOneElement(
                          ${product.id},${quantity}, ${idProductsSplit})">-</button>
                        <button onclick="addOneElement(${
                          product.id
                        },${quantity})">+</button>
                    </p>
                    <p class="cart-product-delete">
                        <button onclick="deleteProductCart(${
                          product.id
                        })">Eliminar</button>
                    </p>
                </div>
            </div>
            `;
        }
      });
    });
  }
  //console.log(html);

  document.getElementsByClassName("cart-products")[0].innerHTML = html;
}

function countDuplicatesId(value, arrayIds) {
  let count = 0;
  arrayIds.forEach((id) => {
    if (value == id) {
      count++;
    }
  });
  return count;
}

function removeOneElement(idValue, quantity) {
  let arrayIds2 = localStorage.getItem(CAR_PRODUCTS);
  let arrayIds = arrayIds2.split(",");
  console.log(idValue+ " id del producto");
  console.log(quantity+ " cantidad del producto");
  console.log(arrayIds+ " productos en el local Storage");
  for ( var i = 0; i<arrayIds.length;i++){
    if(!arrayIds[i]){
      console.log("soy nulo en la posicion "+ i);
      arrayIdProductsCart.splice(i,1);
    }
    if(idValue==arrayIds[i]){
      arrayIds.splice(i,1);
      break;
    }
  }
  
  quantity--;
  if(quantity==0){
    deleteAllIds(idValue,arrayIds);
  }
  localStorage.setItem(CAR_PRODUCTS, arrayIds);
  loadProductCart();
  evaluateEmptyLocalStorage();
}

function addOneElement(idValue) {

  const idProductsCart = localStorage.getItem(CAR_PRODUCTS);
  let arrayIdProductsCart = idProductsCart.split(",");
  arrayIdProductsCart.push(idValue);
  localStorage.setItem(CAR_PRODUCTS,arrayIdProductsCart);
  loadProductCart();
 
}

function deleteProductCart(idProduct) {
  const idProductsCart = localStorage.getItem(CAR_PRODUCTS);
  const arrayIdProductsCart = idProductsCart.split(",");
  for ( let i = 0; i< arrayIdProductsCart.length; i++){
    if(!arrayIdProductsCart[i]){
      console.log("soy nulo en la posicion "+ i);
      arrayIdProductsCart.splice(i,1);
    }
  }
  
  const resultIdDeleted = deleteAllIds(idProduct, arrayIdProductsCart);
  let count = 0;
  let idString = "";
  if (resultIdDeleted) {
    // los que no estan eliminados
    resultIdDeleted.forEach((id) => {
      if (count < resultIdDeleted.length) {
        console.log(resultIdDeleted[resultIdDeleted.length-1]);
        if(resultIdDeleted[resultIdDeleted.length-1]==","){
          idString += id ;
          count++;
        }else{
        idString += id +",";
        count++;
        }
      } else {
        idString += id;
      }
    });
    
    
  }
  
  loadProductCart(); 
  
  localStorage.setItem(CAR_PRODUCTS, idString);
  evaluateEmptyLocalStorage();
    
}

function deleteAllIds(id, arrayIds) {
  return arrayIds.filter((itemId) => {
    return itemId != id;
  });
}

function evaluateEmptyLocalStorage(){
  var idProductsCart3 = localStorage.getItem(CAR_PRODUCTS);
  
    if(idProductsCart3 =="," || !idProductsCart3){
      localStorage.clear();
      console.log("eliminado el localStorage porque carProductsId esta vacio");
    }
}