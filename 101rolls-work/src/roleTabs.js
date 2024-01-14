const roleTabs = [
    {
        role: "ADMIN",
        pages: [
            {id: "dashboard", title: "Главная"},
            {id: "orders", title: "Заказы"},
            {id: "employees", title: "Сотрудники"},
            {id: "menu", title: "Меню"},
            {id: "restaurants", title: "Рестораны"},
        ]
    }, 
    {
        role: "DELIVERER",
        pages: [
            {id: "download-app", title: "Загрузка приложения"}
        ]
    },
    {
        role: "OPERATOR",
        pages: [
            {id: "address-checking", title: "Обработка адресов"},
            {id: "addresses", title: "Адреса"}
        ]
    },
    {
        role: "CHEF",
        pages: [
            {id: "cooking", title: "Готовка"}
        ]
    }
]