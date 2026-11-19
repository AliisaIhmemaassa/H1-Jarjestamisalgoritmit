# Lajittelualgoritmiraportti

Vertailu kuuden lajittelumenetelmän mitatuista ja teoreettisista suoritusajoista.

## Testiasetelma

- **Algoritmit:** Insertion, Bubble (ennenaikaisella lopetuksella), Shell (n/2 -etäisyydet), Quick (median-of-three), Merge, `Arrays.sort`
- **Datan koot (N):** 10 000 ja 500 000 (myös 100 000 ajettiin)
- **Datan järjestykset:** satunnainen, käänteinen, nouseva
- **Mittaus:** `System.nanoTime()`, jokainen algoritmi lajittelee oman kopionsa samasta datasta, yksi ajo per tapaus

## Teoreettinen monimutkaisuus

| Algoritmi | Nouseva | Satunnainen | Käänteinen |
|---|---|---|---|
| Insertion | n | n² | n² |
| Bubble | n | n² | n² |
| Shell | ~n log n | ~n^1.5 | ~n^1.5 |
| Quick | n log n | n log n | n log n |
| Merge | n log n | n log n | n log n |
| `Arrays.sort` | ~n–n log n | n log n | ~n–n log n |

## Mitatut ajat (ms)

`*` = ei mitattu tässä koossa; arvioitu N = 100 000 -tuloksesta (×25 n²:lle, ×5 n:lle).

**N = 10 000**

| Algoritmi | Satunnainen | Käänteinen | Nouseva |
|---|---|---|---|
| Insertion | 17,03 | 29,55 | 0,29 |
| Bubble | 28,74 | 19,69 | 0,24 |
| Shell | 2,60 | 1,80 | 1,70 |
| Quick | 1,20 | 1,54 | 0,97 |
| Merge | 1,37 | 1,07 | 1,04 |
| `Arrays.sort` | 5,05 | 0,66 | 0,45 |

**N = 500 000**

| Algoritmi | Satunnainen | Käänteinen | Nouseva |
|---|---|---|---|
| Insertion | ~32 700 * | ~66 600 * | ~10 * |
| Bubble | ~164 000 * | ~35 100 * | ~7 * |
| Shell | 49,76 | 12,85 | 9,27 |
| Quick | 41,13 | 20,48 | 21,99 |
| Merge | 43,65 | 23,93 | 23,54 |
| `Arrays.sort` | 45,09 | 2,90 | 2,78 |

## Mitattu vs. teoreettinen

Teoria ei anna sekunteja, vaan kertoo vain, miten aika **kasvaa**, kun N kasvaa. Siksi alla olevissa taulukoissa verrataan kasvutekijää  
(aika suuremmalla N ÷ aika pienemmällä N) siihen, mitä teoria ennustaa.

Odotetut tekijät: n² → ×100 (10k→100k) tai ×2500 (10k→500k); n log n → ×71 (10k→500k); n → ×10 (10k→100k) tai ×50 (10k→500k); n^1.5 → ×354 (10k→500k).

**Hitaat algoritmit, N = 10 000 → 100 000**

| Algoritmi | Data | Teoria | Odotettu | Mitattu |
|---|---|---|---|---|
| Insertion | Satunnainen | n² | ×100 | ×77 |
| Insertion | Käänteinen | n² | ×100 | ×90 |
| Bubble | Satunnainen | n² | ×100 | ×228 |
| Bubble | Käänteinen | n² | ×100 | ×71 |
| Insertion | Nouseva | n | ×10 | ×6,7 |
| Bubble | Nouseva | n | ×10 | ×5,9 |

| Algoritmi | Data | Teoria | Odotettu | Mitattu |
|---|---|---|---|---|
| Quick | Satunnainen | n log n | ×71 | ×34 |
| Quick | Käänteinen | n log n | ×71 | ×13 |
| Quick | Nouseva | n log n | ×71 | ×23 |
| Merge | Satunnainen | n log n | ×71 | ×32 |
| Merge | Käänteinen | n log n | ×71 | ×22 |
| Merge | Nouseva | n log n | ×71 | ×23 |

| Algoritmi | Data | Teoria | Odotettu | Mitattu |
|---|---|---|---|---|
| Shell | Satunnainen | n^1.5 | ×354 | ×19 |
| Shell | Käänteinen | n^1.5 | ×354 | ×7 |
| Shell | Nouseva | n log n | ×71 | ×5,5 |
| `Arrays.sort` | Satunnainen | n log n | ×71 | ×8,9 |
| `Arrays.sort` | Käänteinen | n–n log n | ×50–71 | ×4,4 |
| `Arrays.sort` | Nouseva | n–n log n | ×50–71 | ×6,2 |

## Analyysi

- **n²-algoritmit vastasivat teoriaa.** Insertion ja Bubble olivat selvästi hitaimpia satunnaisella ja käänteisellä datalla. N = 100 000:lla niiden ajoaika oli 1,3–6,6 sekuntia, kun taas muilla oli noin 10 ms.
  Bubble satunnaisella datalla kasvoi odotettua enemmän (×228 ×100:n sijaan), todennäköisesti, koska algoritmi ajettiin vain kerran, eli satunnainen data oli vain yksi otos.
- **Nouseva data on paras tapaus.** Insertion ja Bubble olivat lähes yhtä nopeita kuin n log n -algoritmit (alle 2 ms N = 100 000:lla), mikä on odotettua lineaarista käyttäytymistä.
- **Quick, Merge ja `Arrays.sort` olivat lähes yhtä nopeita satunnaisella datalla** (41–45 ms N = 500 000:lla).
- **Median-of-three toimii.** Quick sort pysyi nopeana nousevalla ja käänteisellä datalla sen sijaan, että olisi heikentynyt n²-tasolle.
- **`Arrays.sort` on nopein lajitellulla ja käänteisellä datalla** (noin 3 ms N = 500 000:lla), koska Java havaitsee jo järjestyksessä olevat osuudet.
- **Shell oli paljon parempi kuin n^1.5-pahin tapaus** ja käyttäytyi tällä datalla lähes kuin n log n.
- **Käänteinen data:** Insertion oli noin 2× hitaampi kuin satunnaisella datalla (jokainen alkio on siirrettävä), kun taas Bubble oli nopeampi kuin satunnaisella datalla.
