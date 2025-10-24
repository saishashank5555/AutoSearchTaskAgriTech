import React, { useState, useEffect, useRef } from 'react';
import SuggestionList from './SuggestionList';
import { fetchSuggestions } from '../services/api';

export default function SearchBox({ onSelect }) {
  const [query, setQuery] = useState('');
  const [suggestions, setSuggestions] = useState([]);
  const [open, setOpen] = useState(false);
  const [active, setActive] = useState(-1);
  const [noResults, setNoResults] = useState(false);

  const timer = useRef(null);

  useEffect(() => {
    clearTimeout(timer.current);
    if (!query.trim()) {
      setSuggestions([]);
      setOpen(false);
      setNoResults(false);
      return;
    }

    timer.current = setTimeout(async () => {
      const listOfresult = await fetchSuggestions(query.trim());
      setSuggestions(listOfresult);
      setOpen(true);
      setActive(-1);
      setNoResults(listOfresult.length === 0);
    }, 300);
  }, [query]);

  const selectItem = (item) => {
    setQuery(item);
    setOpen(false);
    onSelect && onSelect(item);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (query.trim()) onSelect && onSelect(query.trim());
    setOpen(false);
    console.log(query);
  };

  return (
    <div style={{ position: 'relative', flex: 1 , background:'red'}}>
      <form onSubmit={handleSubmit} style={{ display: 'flex'}}>
        <input
          type="text"
          onChange={(e) => setQuery(e.target.value)}
          value={query}
          onFocus={() => suggestions.length && setOpen(true)}
          placeholder="Search products, e.g. Rice"
          style={{ flex: 1, padding: '8px', border: '1px solid #ccc', borderRadius: '5px 0 0 5px', outline: 'none' }}
        />
        <button
          type="submit"
          style={{ padding: '8px 10px', border: '1px solid #ccc', borderRadius: '0 5px 5px 0', cursor: 'pointer', backgroundColor: '#4f46e5', color: 'white' }}
        >
          Search
        </button>
      </form>

      {open && (
        <div>
          {suggestions.length > 0 ? (
            <SuggestionList
              suggestions={suggestions}
              active={active}
              onSelect={selectItem}
              setActive={setActive}
            />
          ) : (
            noResults && (
              <div style={{ position: 'absolute', top: '40px', left: 0, right: 0, backgroundColor: 'white', border: '1px solid #ccc', borderRadius: '5px', padding: '8px 12px', zIndex: 1000, color: '#555' }}>
                No results found
              </div>
            )
          )}
        </div>
      )}
    </div>
  );
}
