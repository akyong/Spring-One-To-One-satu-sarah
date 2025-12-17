# InstructorApp (One-to-One JPA Mapping)

Ini project kecil buat latihan **relasi One-to-One** di JPA/Hibernate (via Spring Boot).
Fokusnya ada di 2 entity:

- `Instructor`
- `InstructorDetail`

Tujuannya: ngerti **foreign key ada di table mana**, kapan pakai **@JoinColumn**, kapan pakai **mappedBy**, dan efek **cascade** pas save/delete.

---

## Gambaran Relasi: Instructor <-> InstructorDetail

Secara konsep:
- **1 Instructor punya 1 InstructorDetail**
- **1 InstructorDetail cuma punya 1 Instructor**

Di database biasanya bentuknya kayak gini (versi gampangnya):

- Table `instructor`
    - `id`
    - `first_name`
    - `last_name`
    - `email`
    - `instructor_detail_id`  ← ini yang jadi **Foreign Key**
- Table `instructor_detail`
    - `id`
    - `youtube_channel`
    - `hobby`

Jadi kuncinya:
> Kalau di table `instructor` ada kolom `instructor_detail_id`, berarti **table instructor yang nyimpen FK** ke instructor_detail.

Makanya mapping yang “punya FK” itu yang **wajib pakai `@JoinColumn`**.

---

## Unidirectional vs Bidirectional (yang sering bikin kepleset)

### 1) Unidirectional One-to-One (satu arah)
Artinya:
- Dari `Instructor` kamu bisa akses `InstructorDetail`
- Tapi dari `InstructorDetail` kamu **tidak** bisa balik akses `Instructor`

Biasanya dipakai kalau emang kebutuhan navigasinya cuma satu arah, biar simpel.

### 2) Bidirectional One-to-One (dua arah)
Artinya:
- `Instructor` bisa akses `InstructorDetail`
- `InstructorDetail` juga bisa akses balik ke `Instructor`

Nah ini baru butuh 2 sisi mapping, dan salah satu sisi harus bilang:
> “gue yang kebagian foreign key” vs “gue cuma ngikutin (mappedBy)”.

---

## Aturan Praktis: @JoinColumn vs mappedBy

### Kalau entity-nya punya foreign key di table-nya
Pakai:
- `@JoinColumn(name = "...")`

Contoh logika:
- di table `instructor` ada `instructor_detail_id`
- berarti di entity `Instructor` kita pakai `@JoinColumn(name = "instructor_detail_id")`

### Kalau entity-nya *tidak* punya foreign key (dia cuma kebagian “relasi balik”)
Pakai:
- `mappedBy = "namaFieldYangNgaturRelasiDiSisiFK"`

Contoh gambaran:
- FK ada di table `user_profile` (punya `user_id`)
- maka di entity `User` (yang gak punya FK) pakai `@OneToOne(mappedBy = "user")`

Intinya:
> **Yang nyimpen FK = owning side** (biasanya pakai `@JoinColumn`)  
> **Yang gak nyimpen FK = inverse side** (pakai `mappedBy`)

---

## Cascade (yang bikin save/delete jadi “ikut kebawa”)

Di project ini relasi dari `Instructor` ke `InstructorDetail` pakai **cascade ALL**.

Efeknya:
- kamu save `Instructor` → `InstructorDetail` ikut kesave
- kamu delete `Instructor` → `InstructorDetail` ikut kedelete

Jadi enak buat latihan, tapi di real project kadang harus mikir dulu:
- kalau delete instructor, apakah detail-nya memang harus ikut hilang?
- kalau tidak, jangan pakai ALL, atau pilih cascade yang lebih aman.

---

## Flow yang Dites (Create / Find / Delete)

Project ini dijalankan via `CommandLineRunner`, jadi tiap run aplikasi kamu bisa ganti skenario.

### 1) Create Instructor + InstructorDetail
Biasanya langkahnya:
1. bikin object Instructor
2. bikin object InstructorDetail
3. set detail ke instructor
4. save instructor (detail ikut kesave karena cascade)

Yang penting:
- **relasi diset dari sisi yang owning (yang punya FK)**

### 2) Find Instructor by ID
Pas find instructor:
- instructor ke-load
- detail bisa kamu akses lewat getter relasinya

### 3) Delete Instructor by ID
Pas delete instructor:
- kalau cascade ALL aktif, detail juga ikut kehapus

Catatan penting:
- kalau kamu bikin bidirectional, pastikan relasi tidak bikin loop pas `toString()`/log (ini sering jadi jebakan juga).

---

## Struktur Folder yang Relevan

Yang paling relevan untuk materi ini biasanya ada di:
- `InstructorApp/src/main/java/.../entity/` → entity mapping
- `InstructorApp/src/main/java/.../dao/` → layer akses data
- `InstructorApp/src/main/java/.../InstructorAppApplication` → runner buat test create/find/delete

---

## Checklist Cepat (biar gak ketuker)

- FK ada di table mana?  
  → lihat kolom `..._id` itu nempel di table siapa

- Entity yang table-nya nyimpen FK  
  → pakai `@JoinColumn`

- Entity yang cuma “relasi balik”  
  → pakai `mappedBy`

- cascade ALL  
  → save/delete ikut nyeret entity yang berelasi

---

## Next Step (kalau mau lanjut materi)
Kalau udah paham One-to-One, biasanya next yang seru:
- One-to-Many / Many-to-One (yang paling sering kepake di dunia nyata)
- fetch type (EAGER vs LAZY)
- handling bidirectional biar gak infinite loop (Jackson/JSON + DTO)

---

Kalau kamu mau, kirim juga file `InstructorDetail`-nya, nanti aku bisa rapihin README ini biar makin “nempel” sama implementasi persis kamu (unidirectional/bidirectional-nya udah dipakai yang mana).