create table Users
	(email		varchar(100),
	 firstname	varchar(20)  not null,
	 lastname	varchar(20) not null,
	 passwd varchar(20)  not null,
	 role varchar(10) check (role in ('agent', 'renter')),
	 primary key (email)
	);

create table Agent
  (email varchar(100),
   estate_agency varchar(100),
   job_title varchar(20),
   phone varchar(10),
   primary key (email),
   foreign key (email) references Users (email)
		on delete cascade
  );

create table Renter
  (email varchar(100),
   age numeric(2,0) check (age > 18),
   sex varchar(10) check (sex in ('male', 'female')),
   job varchar(20),
   phone varchar(10),
   primary key (email),
   foreign key (email) references Users (email)
		on delete cascade
  );

create table Address
  (address_id varchar(32),
   email varchar(100),
   city varchar(20),
   state varchar(20),
   address varchar(200),
   primary key (address_id),
   foreign key (email) references Users (email)
		on delete cascade
  );

create table Preference
  (email varchar(100),
   desired_date date,
   preferred_location varchar(100),
   budget numeric(7,2) check (budget > 0),
   primary key (email),
   foreign key (email) references Renter (email)
		on delete cascade
  );

create table CreditCard
  (card_no varchar(16),
   email varchar(100),
   address_id varchar(100),
   bank varchar(40) not null,
   primary key (card_no),
   foreign key (email) references Renter (email)
		on delete cascade,
	 foreign key (address_id) references Address (address_id)
		on delete set null
  );

create table Reward
  (reward_id varchar(64),
   email varchar(100),
   point_count numeric(6,0) check (point_count > 0),
   primary key (reward_id),
   foreign key (email) references Renter (email)
		on delete cascade
  );

create table RewardDetail
  (email varchar(100),
   detail_id varchar(100),
   operation varchar(30),
   reward_id varchar(64),
   point numeric(6,0) check (point > 0),
   opt_date date,
   primary key (detail_id),
   foreign key (reward_id) references Reward (reward_id)
		on delete cascade
  );

create table Property
  (property_id varchar(40),
   email varchar(100),
   property_type varchar(10) check (property_type in ('house', 'apartment','commercial')),
   description varchar(1000),
   city varchar(20),
   state varchar(20),
   address varchar(200),
   availability numeric(1,0) check (availability=0 or availability = 1) default 1,
   rental_price numeric(7,2) check (rental_price > 0),
   square_footage numeric(5,2) check (square_footage > 0),
   primary key (property_id),
   foreign key (email) references Agent (email)
		on delete set null
  );

create table House
  (property_id varchar(40),
   rooms_number numeric(1,0) check (rooms_number > 0 ),
   primary key (property_id),
   foreign key (property_id) references Property (property_id)
		on delete cascade
  );

create table Apartment
  (property_id varchar(40),
   rooms_number numeric(1,0) check (rooms_number > 0 ),
   building_type varchar(10) not null,
   primary key (property_id),
   foreign key (property_id) references Property (property_id)
		on delete cascade
  );

create table Commercial
  (property_id varchar(40),
   business_type varchar(20) not null,
   primary key (property_id),
   foreign key (property_id) references Property (property_id)
		on delete cascade
  );


create table Neighborhood
  (property_id varchar(40),
   neighborhood_id varchar(20) not null,
   crime_rate numeric(6,4) check (crime_rate >= 0),
   primary key (property_id,neighborhood_id),
   foreign key (property_id) references Property (property_id)
		on delete cascade
  );

create table Nearby
  (property_id varchar(40),
   nearby_id numeric(2,0),
   type varchar(10) not null,
   name varchar(20) not null,
   description varchar(200),
   images varchar(100),
   primary key (property_id,nearby_id),
   foreign key (property_id) references Property (property_id)
		on delete cascade
  );

create table PropertyBooking
  (property_id varchar(40),
   booking_date date,
   status varchar(1),
   email varchar(100),
   card_no varchar(16),
   primary key (property_id,booking_date,status),
   foreign key (email) references Renter (email),
   foreign key (card_no) references CreditCard (card_no)
  );
