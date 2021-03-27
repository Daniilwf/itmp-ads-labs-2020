var
  n, i: integer;
  a: array [1..200000] of integer;
procedure heapsort(var v: integer);
var
  k, l: integer;
  x: integer;
  procedure sift(k, l: integer);
  var
    i, j: integer; x: integer;
  begin
    i := k; j := 2 * k; x := a[k];
    if (j < l) and (a[j] < a[j + 1]) then j := j + 1;
    while (j <= l) and (x < a[j]) do 
    begin
      a[i] := a[j]; i := j; j := 2 * j;
      if (j < l) and (a[j] < a[j + 1]) then j := j + 1;
    end;
    a[i] := x
  end;
 
begin
  k := (n Div 2) + 1; l := n;
  while k > 1 do begin k := k - 1; sift(k, l) end;
  while l > 1 do 
  begin
    x := a[1]; a[1] := a[l]; a[l] := x; l := l - 1; sift(k, l)
  end;
end;
 
begin
  readln(n);
  for i := 1 to n do
   read(a[i]);
   heapsort(a[i]);
  for i := 1 to n do
    write(a[i] + ' ');
end.