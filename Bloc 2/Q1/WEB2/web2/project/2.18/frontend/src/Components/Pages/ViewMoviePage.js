import { clearPage } from '../../utils/render';

const ViewMoviePage = async () => {
  try {
    clearPage();

    const response = await fetch('https://localhost:3000/movies');

    if (!response.ok) throw new Error(`fetch error : ${response.status} : ${response.statusText}`);

    const movies = await response.json();

    renderTitle();
    createTableMovies(movies);
  } catch (err) {
    console.error('HomePage::error: ', err);
  }
};

function renderTitle() {
  const main = document.querySelector('main');

  main.innerHTML += `
  <h3 style="margin:30px;">
    Movies list
  </h3>`;

};

function createTableMovies (movies) {

  const main = document.querySelector('main');

  const table = getMoviesTableAsNode(movies);
  main.appendChild(table);

};

function getMoviesTableAsNode(movies) {
  const tableWrapper = document.createElement('div');
  tableWrapper.className = 'container';
  tableWrapper.style.padding = '20px';
  const table = document.createElement('table');
  table.className = 'table table-hover';

  const thead = document.createElement('thead');
  table.appendChild(thead);
  const header = document.createElement('tr');
  const header1 = document.createElement('th');
  header1.innerText = '#';
  header1.scope = 'col';
  const header2 = document.createElement('th');
  header2.innerText = 'Title';
  header2.scope = 'col';
  const header3 = document.createElement('th');
  header3.innerText = 'Duration';
  header3.scope = 'col';
  const header4 = document.createElement('th');
  header4.innerText = 'Budget';
  header4.scope = 'col';
  header.appendChild(header1);
  header.appendChild(header2);
  header.appendChild(header3);
  header.appendChild(header4);
  thead.appendChild(header);

  const tbody = document.createElement('tbody');
  tableWrapper.appendChild(table);
  table.appendChild(tbody);
  

  movies?.forEach((movie) => {
    const line = document.createElement('tr');
    const id = document.createElement('th');
    id.scope = 'row';
    id.innerText = movie.id;
    line.appendChild(id);
    const title = document.createElement('td');
    const titleLink = document.createElement('a');
    titleLink.href = movie.link;
    titleLink.textContent = movie.title;
    title.appendChild(titleLink);
    const duration = document.createElement('td');
    const budget = document.createElement('td');
    duration.innerText = movie.duration;
    budget.innerText = movie.budget;
    line.appendChild(title);
    line.appendChild(duration);
    line.appendChild(budget);
    tbody.appendChild(line);
  });

  return tableWrapper;
}

export default ViewMoviePage;
