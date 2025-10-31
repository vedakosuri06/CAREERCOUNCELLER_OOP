(function(){
  // Small animation helper: entrance animations and scroll-triggered reveals
  function onReady(fn){
    if(document.readyState !== 'loading') fn(); else document.addEventListener('DOMContentLoaded', fn);
  }

  function addClass(el, cls){ if(el) el.classList.add(cls); }

  onReady(function(){
    // Mark body so CSS can scope if needed
    document.body.classList.add('animate-page');

    // Entrance animations (staggered)
    // Header, hero and instruction card
    const header = document.querySelector('.header');
    const hero = document.querySelector('.hero-section');
    const instr = document.querySelector('.instruction-wrap');

    setTimeout(()=> addClass(header, 'animate-fade-in'), 80);
    setTimeout(()=> addClass(hero, 'animate-fade-up'), 180);
    setTimeout(()=> addClass(instr, 'animate-fade-up'), 260);

    // Stagger features and counts
    document.querySelectorAll('.stagger').forEach(function(s){
      // trigger stagger animation with a slight delay so it flows after hero
      setTimeout(()=> s.classList.add('animate-stagger'), 360);
    });

    // simple float on personality wheel if present
    const wheel = document.querySelector('.personality-wheel');
    if(wheel) addClass(wheel, 'animate-float');

    // Intersection observer for scroll reveals
    const revealSelector = [
      '.feature', '.personality-type', '.instruction-card', '.counts-card', '.count-item',
      '.quiz-question', '.result-card', '.card', '.hero-left', '.hero-right'
    ].join(',');

    const toReveal = Array.from(document.querySelectorAll(revealSelector));

    if('IntersectionObserver' in window && toReveal.length){
      const io = new IntersectionObserver((entries)=>{
        entries.forEach(entry=>{
          if(entry.isIntersecting){
            entry.target.classList.add('animate-fade-up');
            io.unobserve(entry.target);
          }
        });
      }, {threshold:0.12});

      toReveal.forEach(el=> io.observe(el));
    } else {
      // fallback: animate all immediately
      toReveal.forEach(el=> el.classList.add('animate-fade-up'));
    }

    // Make CTA pop a little on first hover-ready frame
    const cta = document.querySelector('.cta-button');
    if(cta){ setTimeout(()=> cta.classList.add('animate-zoom-in'), 400); }
  });
})();
