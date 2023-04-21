# Spring REST сервер связанный с [JavaFX приложением](https://github.com/parazit1k/Java/tree/main/OldCode/CursWork/client_JavaFX/src/client)

# Аннотации
@Id и @GenerateValue, первая показывает базе данных, что это id, вторая задаёт уникальный номер для каждого id.   
@ManyToMany, @ManyToOne, @OneToOne – Обозначают связи двух сущностей.     
@JoinColumn добавляет общую колонку с id.   
@JoinTable создаёт отдельную таблицу с колонками, которые мы указываем в «joinColumns» и «inverseJoinColumns».  

@GetMapping – Отдают информацию.  
@PostMapping – Принимают информацию.  
@PutMapping – Изменяют существующую информацию.   
@DeleteMapping – Удаляют существующую информацию.   
# База данных 
*Папка [Entity](https://github.com/parazit1k/Java/tree/main/OldCode/CursWork/kursworkfinal/src/main/java/com/parazitik/kursworkfinal/entity)*   
![image](https://user-images.githubusercontent.com/56428902/233674104-dcd15cf0-d23c-436a-9606-328088cd91cb.png)

Краткое описание каждой таблицы:
*	UserEntity – Покупатели,
*	ProductEntity – Товары в магазине
*	BrandEntity – Бренды
*	BinEntity – Корзина
*	OrderEntity – Заказы

Связи сущностей:
*	UserEntity – BinEntity One to One
*	BinEntity – ProductEntity Many to Many
*	BrandEntity – ProductEntity One to Many
*	OrderEntity – UserEntity Many to One
*	OrderEntity – ProductEntity Many to Many

# Репозитории
![image](https://user-images.githubusercontent.com/56428902/233675527-3f0c4ac6-44da-4d5d-bdad-399a7e1f57e7.png)

# Контроллеры
![image](https://user-images.githubusercontent.com/56428902/233675589-5d13a8cb-84c8-4976-a66f-ef5cdc0ca145.png)
