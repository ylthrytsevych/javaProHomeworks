 const display = document.getElementById('display');
     const operators = ['+', '-', '*', '/'];
     // перевіряє, чи введений символ - це один з тих вище мат операторів
         function isOperator(c) {
             return operators.includes(c);
         }

    function appendCharacter(char) {
        if (display.value === "Помилка" || display.value === "Ділення на 0!") {
            display.value = "";
        }
        const lastChar = display.value.slice(-1);  //щоб перевірити чи не було вже крапки чи математичних операторів

        if (display.value === "" && (char === '*' || char === '/' || char === '+' || char === '.')  ) {
            return; // Ігноруємо натискання якщо пусто і починати введення з мат символів, хоча - дозволяю для мінусових чиесел
        }

        if (char === '.' && isOperator(lastChar)) {
            return; // крапка після мат символів
        }
        if (char === '.') {
                    // Розбиваємо весь рядок на частини за допомогою математичних знаків.
                    // Наприклад, рядок "4.5+6" розіб'ється на масив ["4.5", "6"].
        const parts = display.value.split(/[\+\-\*\/]/);
                // Беремо останнє число, яке користувач зараз вводить (у нашому випадку це "6")
        const currentNumber = parts[parts.length - 1];
        // Якщо в цьому поточному числі вже є крапка — забороняємо ставити другу
        if (currentNumber.includes('.')) {
            return;
            }
        }

        if (isOperator(char) && isOperator(lastChar)) {
       //якщо перед новим введенням вже стояв знак то замінити на новий а не додавати
        display.value = display.value.slice(0, -1) + char;
        return;
        }
        //просто додати
        display.value += char;
    }

    function clearDisplay() {
        display.value = "";
    }

    function backspace() {
        if (display.value === "Помилка" || display.value === "Ділення на 0!") {
            display.value = "";
        } else {
            display.value = display.value.slice(0, -1);
        }
    }

//знак дорівнює
    function calculate() {
        const expression = display.value;
        if (expression === "") {
            return; // пусто - ну то ніц
        }

        if (expression.includes('/0')) {
            display.value = "Ділення на 0 ЗАБОРОНЕНЕ!";
            return;
        }

        // рядок "12+3*4" перетвориться на масив ["12", "3", "4"]
        let numbers = expression.split(/[\+\-\*\/]/);
        let ops = expression.replace(/[0-9\.]/g, "").split("");
         if (expression.startsWith("-")) {
                    numbers.shift(); // видаляємо пустий перший елемент
                    numbers[0] = "-" + numbers[0]; // робимо перше число від'ємним
                    ops.shift(); // видаляємо перший мінус зі списку знаків, бо це не дія, а знак числа
                }
         let result = parseFloat(numbers[0]);
         for (let i = 0; i < ops.length; i++) {
                     let nextNum = parseFloat(numbers[i + 1]);
                     let op = ops[i];

                     if (op === '+') result = add(result, nextNum);
                     if (op === '-') result = subtract(result, nextNum);
                     if (op === '*') result = multiply(result, nextNum);
                     if (op === '/') {
                         if (nextNum === 0) {
                             display.value = "Ділення на 0 ЗАБОРОНЕНЕ!";
                             return;
                         }
                         result = divide(result, nextNum);
                     }
                 }

                 // 4. Виводимо результат
                 if (isNaN(result) || result === undefined) {
                     display.value = "Помилка";
                 } else {
                     display.value = result;
                 }

    }

    function add(a, b) { return a + b; }

    function subtract(a, b) { return a - b; }

    function multiply(a, b) { return a * b; }

    function divide(a, b) {
        if (b === 0) return "Ділення на 0 ЗАБОРОНЕНЕ!";
        return a / b;
    }