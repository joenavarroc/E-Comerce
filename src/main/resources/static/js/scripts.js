
    let cart = [
        { id: 1, name: 'Planta de tomate', price: 5, quantity: 1 },
        { id: 2, name: 'Rosas rojas', price: 8, quantity: 2 }
    ];

    function updateCart() {
        const cartItemsContainer = document.getElementById('cart-items');
        const totalElement = document.getElementById('cart-total');
        
        // Limpiar la lista de artículos del carrito
        cartItemsContainer.innerHTML = '';
        
        let total = 0;
        cart.forEach(item => {
            total += item.price * item.quantity;
            
            const listItem = document.createElement('li');
            listItem.classList.add('list-group-item');
            listItem.innerHTML = `
                <div class="d-flex justify-content-between align-items-center">
                    <span>${item.name} x${item.quantity} - ${item.price}$</span>
                    <div>
                        <button class="btn btn-sm btn-danger me-2" onclick="removeFromCart(${item.id})">Eliminar</button>
                        <button class="btn btn-sm btn-warning me-2" onclick="changeQuantity(${item.id}, -1)">-</button>
                        <button class="btn btn-sm btn-warning" onclick="changeQuantity(${item.id}, 1)">+</button>
                    </div>
                </div>
            `;
            cartItemsContainer.appendChild(listItem);
        });
        
        totalElement.textContent = total;
    }

    function removeFromCart(id) {
        cart = cart.filter(item => item.id !== id);
        updateCart();
    }

    function changeQuantity(id, delta) {
        const item = cart.find(item => item.id === id);
        if (item) {
            item.quantity = Math.max(1, item.quantity + delta);
            updateCart();
        }
    }

    // Inicializar el carrito
    updateCart();



    // Cambiar entre los formularios de login y registro
    document.getElementById('go-to-register').addEventListener('click', function() {
        document.getElementById('login-form').style.display = 'none';
        document.getElementById('register-form').style.display = 'block';
        document.getElementById('authModalLabel').textContent = 'Registrarse';
    });

    document.getElementById('go-to-login').addEventListener('click', function() {
        document.getElementById('login-form').style.display = 'block';
        document.getElementById('register-form').style.display = 'none';
        document.getElementById('authModalLabel').textContent = 'Iniciar sesión';
    });



  const year = new Date().getFullYear();
  document.querySelectorAll('.year').forEach(el => el.textContent = year);

  
  
 // Abrir modal vacío para agregar
 function openProductForm() {
  document.getElementById('productForm').reset();
  document.getElementById('product-id').value = '';
  const modal = new bootstrap.Modal(document.getElementById('productFormModal'));
  modal.show();
}

// Guardar o editar un producto
// Abrir modal vacío para agregar
function openProductForm() {
  document.getElementById('productForm').reset();
  document.getElementById('product-id').value = '';
  const modal = new bootstrap.Modal(document.getElementById('productFormModal'));
  modal.show();
}

// Función para guardar el producto
function saveProduct(event) {
  event.preventDefault();

  const name = document.getElementById('product-name').value;
  const price = document.getElementById('product-price').value;
  const imageUrlInput = document.getElementById('product-image-url').value;
  const imageFileInput = document.getElementById('product-image-file').files[0];
  const description = document.getElementById('product-description').value;
  const inStock = document.getElementById('newStockSwitch').checked;

  const id = document.getElementById('product-id').value || 'modal' + Date.now(); // ID único

  function continuarConImagen(image) {
    const existingCard = document.querySelector(`.btn-edit[data-id='${id}']`)?.closest('.col');
    const existingModal = document.getElementById(id);

    if (existingCard && existingModal) {
      existingCard.querySelector('.card-title').textContent = name;
      existingCard.querySelector('.card-text').textContent = `$${price}`;
      const img = existingCard.querySelector('img');
      img.src = image;
      img.alt = name;
      img.setAttribute('onclick', `document.getElementById('${id}').showModal()`);

      existingModal.querySelector('h2').textContent = name;
      existingModal.querySelector('img').src = image;
      existingModal.querySelector('p').textContent = description;
      existingModal.querySelector('p + p').innerHTML = `<strong>Precio:</strong> $${price}`;

      if (!inStock) {
        existingCard.querySelector('.btn-success').disabled = true;
        existingCard.querySelector('.btn-success').textContent = 'Sin stock';
      } else {
        existingCard.querySelector('.btn-success').disabled = false;
        existingCard.querySelector('.btn-success').textContent = 'Añadir al carrito';
      }
    } else {
      const cardHTML = `
        <div class="col">
          <div class="card h-100 card-borde">
            <img src="${image}" class="card-img-top" alt="${name}" onclick="document.getElementById('${id}').showModal()">
            <div class="card-body">
              <h6 class="card-title">${name}</h6>
              <p class="card-text">$${price}</p>
              <div class="d-flex mb-2">
                <label class="me-2">Cant.:</label>
                <input type="number" min="1" value="0" class="form-control form-control-sm" style="width: 60px;">
              </div>
              <div class="d-grid gap-2">
                <button class="btn btn-success">Añadir al carrito</button>
                <button class="btn btn-warning btn-edit" data-id="${id}">Editar</button>
                <button class="btn btn-danger btn-delete" data-id="${id}">Eliminar</button>
              </div>
            </div>
          </div>
        </div>

        <dialog id="${id}">
          <h2>${name}</h2>
          <img src="${image}" style="width: 100%;">
          <p>${description}</p>
          <p><strong>Precio:</strong> $${price}</p>
          <button onclick="document.getElementById('${id}').close()">Cerrar</button>
        </dialog>
      `;

      document.getElementById('productContainer').insertAdjacentHTML('beforeend', cardHTML);
      attachProductCardEvents();

      if (!inStock) {
        const newCard = document.querySelector(`.btn-edit[data-id='${id}']`).closest('.col');
        newCard.querySelector('.btn-success').disabled = true;
        newCard.querySelector('.btn-success').textContent = 'Sin stock';
      }
    }

    bootstrap.Modal.getInstance(document.getElementById('productFormModal')).hide();
  }

  // Decide si usar URL o archivo
  if (imageUrlInput) {
    continuarConImagen(imageUrlInput);
  } else if (imageFileInput) {
    const reader = new FileReader();
    reader.onload = function (e) {
      continuarConImagen(e.target.result);
    };
    reader.readAsDataURL(imageFileInput);
  } else {
    alert("Por favor ingresá una URL o subí una imagen.");
  }
}


// Función para habilitar/deshabilitar stock
function cambiarDisponibilidadNuevo(checkbox) {
  // Obtén el ID del producto de alguna forma
  const id = document.getElementById('product-id').value || 'modal' + Date.now();
  
  // Obtén el botón de "Guardar" correspondiente a este producto
  const saveButton = checkbox.closest('.modal-content').querySelector('.btn-success');
  
  // Verifica el estado del checkbox
  const inStock = checkbox.checked;
  
  if (!inStock) {
    saveButton.disabled = true;
    saveButton.textContent = 'Producto sin stock';
  } else {
    saveButton.disabled = false;
    saveButton.textContent = 'Guardar';
  }
}

// FUNCIONES PARA EDITAR Y ELIMINAR PRODUCTOS (como las tenías antes)
function attachProductCardEvents() {
  document.querySelectorAll('.btn-delete').forEach(btn => {
    btn.onclick = function () {
      const card = this.closest('.col');
      const modalId = card.querySelector('img').getAttribute('onclick').match(/'(.*?)'/)[1];
      const modal = document.getElementById(modalId);
      card.remove();
      if (modal) modal.remove();
    };
  });

  document.querySelectorAll('.btn-edit').forEach(btn => {
    btn.onclick = function () {
      const card = this.closest('.col');
      const id = btn.getAttribute('data-id');
      const title = card.querySelector('.card-title').textContent.trim();
      const price = card.querySelector('.card-text').textContent.replace('$', '').trim();
      const imgSrc = card.querySelector('img').src;
      const modal = document.getElementById(id);
      const description = modal ? modal.querySelector('p').textContent.trim() : '';
      const inStock = card.querySelector('.btn-success').textContent === 'Añadir al carrito';

      document.getElementById('product-id').value = id;
      document.getElementById('product-name').value = title;
      document.getElementById('product-price').value = price;
      document.getElementById('product-image-url').value = imgSrc;
      document.getElementById('product-image-file').value = ""; 
      document.getElementById('product-description').value = description;
      document.getElementById('newStockSwitch').checked = inStock;

      const formModal = new bootstrap.Modal(document.getElementById('productFormModal'));
      formModal.show();
    };
  });
}

// CARRUSEL
function attachCarouselEvents() {
  document.querySelectorAll('.btn-edit-carousel').forEach(btn => {
    btn.onclick = function () {
      const card = this.closest('.card');
      const id = btn.getAttribute('data-id');
      const title = card.querySelector('.card-title').textContent.trim();
      const price = card.querySelector('.card-text').textContent.replace('$', '').trim();
      const imgSrc = card.querySelector('img').src;
      const modal = document.getElementById(id);
      const description = modal ? modal.querySelector('p').textContent.trim() : '';

      document.getElementById('product-id').value = id;
      document.getElementById('product-name').value = title;
      document.getElementById('product-price').value = price;
      document.getElementById('product-image-url').value = imgSrc;
      document.getElementById('product-image-file').value = ""; 
      document.getElementById('product-description').value = description;

      const formModal = new bootstrap.Modal(document.getElementById('productFormModal'));
      formModal.show();
    };
  });
}
function saveProduct(event) {
  event.preventDefault();

  const id = document.getElementById('product-id').value;
  const name = document.getElementById('product-name').value;
  const price = document.getElementById('product-price').value;
  const image = document.getElementById('product-image-url').value;
  const description = document.getElementById('product-description').value;

  const editBtn = document.querySelector(`.btn-edit-carousel[data-id="${id}"]`);
  const card = editBtn.closest('.card');

  // Actualizar tarjeta del carrusel
  card.querySelector('.card-title').textContent = name;
  card.querySelector('.card-text').textContent = `$${price}`;
  card.querySelector('img').src = image;

  // Actualizar contenido del modal personalizado <dialog>
  const modal = document.getElementById(id);
  if (modal) {
    const h2 = modal.querySelector('h2');
    const img = modal.querySelector('img');
    const descriptionP = modal.querySelectorAll('p')[0];
    const priceP = modal.querySelectorAll('p')[1];

    if (h2) h2.textContent = name;
    if (img) img.src = image;
    if (descriptionP) descriptionP.textContent = description;
    if (priceP) priceP.innerHTML = `<strong>Precio:</strong> $${price}`;
  }

  // Cerrar modal de edición
  const modalElement = document.getElementById('productFormModal');
  const bootstrapModal = bootstrap.Modal.getInstance(modalElement);
  bootstrapModal.hide();
}



// TARJETAS INFORMATIVAS
let currentEditingCard = null;

function attachInfoCardEvents() {
  document.querySelectorAll('.btn-edit-info').forEach((btn, index) => {
    btn.addEventListener('click', () => {
      const card = btn.closest('.card');
      const img = card.querySelector('img');
      const text = card.querySelector('.card-text');

      currentEditingCard = card;

      document.getElementById('edit-image-url').value = img.src;
      document.getElementById('edit-image-file').value = "";
      document.getElementById('edit-text').value = text.textContent.trim();

      const modal = new bootstrap.Modal(document.getElementById('editInfoModal'));
      modal.show();
    });
  });
}

function saveCardEdit(event) {
  event.preventDefault();

  const imageUrl = document.getElementById('edit-image-url').value;
  const imageFile = document.getElementById('edit-image-file').files[0];
  const newText = document.getElementById('edit-text').value;

  function continuarConImagen(imagenFinal) {
    if (currentEditingCard) {
      currentEditingCard.querySelector('img').src = imagenFinal;
      currentEditingCard.querySelector('.card-text').textContent = newText;
    }
    bootstrap.Modal.getInstance(document.getElementById('editInfoModal')).hide();
  }

  if (imageUrl) {
    continuarConImagen(imageUrl);
  } else if (imageFile) {
    const reader = new FileReader();
    reader.onload = function (e) {
      continuarConImagen(e.target.result);
    };
    reader.readAsDataURL(imageFile);
  } else {
    alert("Por favor ingresá una URL o subí una imagen.");
  }
}

// ✅ Unificar todo al cargar la página
document.addEventListener("DOMContentLoaded", () => {
  attachProductCardEvents();
  attachInfoCardEvents();
  attachCarouselEvents(); // solo si tenés botones específicos en el carrusel
});


function switchToForgotPassword() {
  const loginModalEl = document.getElementById('authModal');
  const forgotModalEl = document.getElementById('forgotPasswordModal');

  const loginModal = bootstrap.Modal.getInstance(loginModalEl);
  const forgotModal = new bootstrap.Modal(forgotModalEl);

  if (loginModal) {
    loginModalEl.addEventListener('hidden.bs.modal', function onHidden() {
      forgotModal.show();
      loginModalEl.removeEventListener('hidden.bs.modal', onHidden);
    });
    loginModal.hide();
  } else {
    forgotModal.show();
  }
}

document.addEventListener('DOMContentLoaded', () => {
  const forgotModalEl = document.getElementById('forgotPasswordModal');
  const authModalEl = document.getElementById('authModal');

  forgotModalEl.addEventListener('hidden.bs.modal', () => {
    // Mostrar el modal de login solo si NO se mostró mensaje de éxito (o sea, si fue cancelado)
    const resetMsg = document.getElementById('reset-message');
    const fueCancelado = resetMsg.textContent.trim() === "";
    
    if (fueCancelado) {
      const authModal = new bootstrap.Modal(authModalEl);
      authModal.show();
    }
  });
});


// Mostrar opciones de pago basadas en la selección


document.addEventListener('DOMContentLoaded', () => {
  const cartModalEl = document.getElementById('cartModal');
  const paymentModalEl = document.getElementById('paymentModal');
  const checkoutBtn = document.getElementById('checkoutBtn');
  const submitBtn = document.getElementById('submitPaymentBtn');

  // Mostrar modal de pago luego de cerrar el carrito
  checkoutBtn.addEventListener('click', () => {
    const cartModal = bootstrap.Modal.getInstance(cartModalEl);
    if (cartModal) cartModal.hide();

    cartModalEl.addEventListener('hidden.bs.modal', function onHidden() {
      const paymentModal = new bootstrap.Modal(paymentModalEl);
      paymentModal.show();
      cartModalEl.removeEventListener('hidden.bs.modal', onHidden);
    });
  });

  // Cambiar opciones visibles según el método de pago
  document.getElementById('payment-method').addEventListener('change', function () {
    document.querySelectorAll('.payment-option').forEach(opt => opt.style.display = 'none');
    const selected = this.value;
    const selectedDiv = document.getElementById(`${selected}-payment`);
    if (selectedDiv) selectedDiv.style.display = 'block';

    // Si es Mercado Pago, deshabilita el botón porque se redirige
    if (selected === 'mercado-pago') {
      submitBtn.style.display = 'none'; // ocultar el botón de Confirmar Pago
      document.getElementById('payWithMP').style.display = 'inline-block';
    } else {
      submitBtn.style.display = 'inline-block';
      document.getElementById('payWithMP').style.display = 'none';
    }
  });

  // Confirmar pago
  submitBtn.addEventListener('click', () => {
    const method = document.getElementById('payment-method').value;

    if (method === 'card') {
      alert('Simulando pago con tarjeta...');
    } else if (method === 'transfer') {
      alert('Simulando orden enviada por correo para transferencia.');
    } else if (method === 'cash') {
      alert('Simulando orden enviada por WhatsApp para pago en efectivo.');
    }

    // Marcar como confirmado
    paymentModalEl.setAttribute('data-payment-confirmed', 'true');

    // Cerrar modal
    bootstrap.Modal.getInstance(paymentModalEl).hide();
  });

  // Volver al carrito si se cerró sin confirmar
  paymentModalEl.addEventListener('hidden.bs.modal', () => {
    const wasConfirmed = paymentModalEl.getAttribute('data-payment-confirmed') === 'true';
    if (!wasConfirmed) {
      const cartModal = new bootstrap.Modal(cartModalEl);
      cartModal.show();
    }
    paymentModalEl.setAttribute('data-payment-confirmed', 'false');
  });

  // 🔗 Redirección a Mercado Pago (poné tu link real generado por backend)
  document.getElementById('payWithMP').addEventListener('click', () => {
    window.location.href = 'https://www.mercadopago.com.ar/checkout/v1/redirect?pref_id=TU_PREFERENCE_ID';
  });
});
