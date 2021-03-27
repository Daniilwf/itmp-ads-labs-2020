var
  a: array [1..200000] of integer;
  b: array [0.. 100] of integer;
  n, k, i, j: integer; 
  
begin
  for i := 0 to 100 do
    b[i] := 0;
  readln(n);
  for i := 1 to n  do 
    read(a[i]);
  for i := 1 to n do 
   b[a[i]] += 1;
  j := 1;
  for i := 0 to 100 do
    for  k := 1 to b[i] do 
    begin 
        a[j] := i; 
        j += 1; 
    end;
  for i:=1 to N do
  Write(a[i] + ' ');
end.